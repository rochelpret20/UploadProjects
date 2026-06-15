import socket
import threading
from datetime import datetime

print("=" * 50)
print("NETWORK PORT SCANNER")
print("=" * 50)

target = input("Ingrese IP objetivo: ")

ports_open = []

def scan_port(port):

    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.settimeout(0.5)

    result = sock.connect_ex((target, port))

    if result == 0:
        print(f"[+] Puerto abierto: {port}")
        ports_open.append(port)

    sock.close()

start = datetime.now()

threads = []

for port in range(1, 1025):

    thread = threading.Thread(
        target=scan_port,
        args=(port,)
    )

    threads.append(thread)
    thread.start()

for thread in threads:
    thread.join()

end = datetime.now()

print("\nResumen")
print("-" * 30)
print("Puertos abiertos:", ports_open)
print("Tiempo:", end - start)