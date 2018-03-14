from Crypto.Cipher import AES
from base64 import b64decode
from cryptography.hazmat.primitives import padding
import os
import json

# Removes the extra characters that the encryptor added to
# make the length of the message a multiple of 16

BLOCK_SIZE_BITS = 128

def unpad(msg):
    unpadder = padding.PKCS7(BLOCK_SIZE_BITS).unpadder()
    data = unpadder.update(msg)
    data += unpadder.finalize()
    return data

nameOfFile = input("[+] Please Enter JSON File To Decrypt: ")

decryption_info = json.load(open(nameOfFile))

extension = b64decode(decryption_info['extension'])
key = b64decode(decryption_info['key'])
iv = b64decode(decryption_info['iv'])
ciphertext = b64decode(decryption_info['ciphertext'])

decryptor = AES.new(key=key,mode=AES.MODE_CBC,IV=iv)

decrypted_content = decryptor.decrypt(ciphertext)
plaintext = unpad(decrypted_content)

file = open(nameOfFile[:nameOfFile.find('.')] + extension, 'w')
file.write(plaintext)
