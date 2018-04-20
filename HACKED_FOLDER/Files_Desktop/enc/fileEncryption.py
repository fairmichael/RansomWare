from Crypto.Cipher import AES
import os
import json
from base64 import b64encode

KEY_SIZE = 32
IV_SIZE = 16

def pad(msg):
	return msg + (16 - len(msg) % 16) * chr(16 - len(msg) % 16)

nameOfFile = input('[*] Please Input File To Encrypt ')

extension = nameOfFile[nameOfFile.find('.')+1:]

key = os.urandom(32)
iv = os.urandom(16)

cipher = AES.new(key = key, mode = AES.MODE_CBC, IV = iv)
plaintext = open(nameOfFile, "rb").read()
plaintext = pad(plaintext)
ciphertext = cipher.encrypt(plaintext)

file = open("Encrypted_" + nameOfFile[:nameOfFile.find('.')] + '.enc', "wb") # User keeps this file, original is deleted.

encryption_info = dict()

encryption_info['key'] = b64encode(key)
encryption_info['iv'] = b64encode(iv)
encryption_info['cipher'] = b64encode(ciphertext)
encryption_info['extension'] = b64encode(extension)

jsonFile = open(nameOfFile[:nameOfFile.find('.')] + ".json", 'w')
json.dump(encryption_info, jsonFile)

file.write(ciphertext)