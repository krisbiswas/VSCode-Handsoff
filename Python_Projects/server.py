import socket            
 
def wlan_ip():
    import subprocess
    result=subprocess.run('ipconfig',stdout=subprocess.PIPE,text=True).stdout.lower()
    scan=0
    for i in result.split('\n'):
        if 'wireless' in i: scan=1
        if scan:
            if 'ipv4' in i: return i.split(':')[1].strip()

ip = (wlan_ip()) 
port = 5000

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((ip, port))
try:
	while True:
		s.listen(5)
		print("Listening " +ip)
		conn, addr = s.accept()
		with conn:
			print('Connected to', addr)
			conn.sendall(b'Connection = OK\nPlayer-Name = Kritine\nFirst-Player = p1')
except KeyboardInterrupt:
	exit(-1)

s.close() 