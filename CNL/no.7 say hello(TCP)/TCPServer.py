import socket

def tcp_server():
    # Create a TCP socket
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # Bind the socket to a specific address and port
    server_address = ('localhost', 12345)
    server_socket.bind(server_address)

    # Listen for incoming connections
    server_socket.listen(1)

    print("TCP Server is waiting for incoming connections...")

    while True:
        client_socket, client_address = server_socket.accept()  # Accept a connection from a client
        print(f"Connected to {client_address}")

        with open('received_file.txt', 'wb') as file:
            while True:
                data = client_socket.recv(1024)  # Receive data from the client
                if not data:
                    break
                file.write(data)  # Write received data to a file

        client_socket.close()
        print(f"Connection with {client_address} closed")

if __name__ == "__main__":
    tcp_server()
