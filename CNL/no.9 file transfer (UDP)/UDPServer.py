import socket

def udp_server():
    # Create a UDP socket
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    
    # Bind the socket to a specific address and port
    server_address = ('localhost', 12345)
    server_socket.bind(server_address)

    print("UDP Server is waiting for incoming connections...")

    while True:
        data, client_address = server_socket.recvfrom(1024)  # Receive data from the client
        if not data:
            break
        with open('received_file.txt', 'ab') as file:
            file.write(data)  # Write received data to a file

    server_socket.close()

if __name__ == "__main__":
    udp_server()
