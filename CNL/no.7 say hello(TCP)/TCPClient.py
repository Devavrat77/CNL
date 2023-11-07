import socket

def tcp_client(file_path):
    # Create a TCP socket
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # Server address and port to connect to
    server_address = ('localhost', 12345)
    client_socket.connect(server_address)

    with open(file_path, 'rb') as file:
        while True:
            data = file.read(1024)  # Read data from the file
            if not data:
                break
            client_socket.send(data)  # Send data to the server

    client_socket.close()

if __name__ == "__main__":
    file_to_send = 'file_to_send.txt'  # Replace with the path to the file you want to send
    tcp_client(file_to_send)
