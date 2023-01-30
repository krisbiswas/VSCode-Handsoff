import socket            
 
host = '192.168.1.6'
port = 5000

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((host, port))
print(host+ " connected")
s.sendall(b'Connection = OK\nPlayer-Name = Kris')
print (s.recv(1024))

s.close() 