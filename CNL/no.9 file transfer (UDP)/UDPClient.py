import socket

def udp_client(file_path):
    # Create a UDP socket
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    
    # Server address and port to send data to
    server_address = ('localhost', 12345)

    with open(file_path, 'rb') as file:
        while True:
            data = file.read(1024)  # Read data from the file
            if not data:
                break
            client_socket.sendto(data, server_address)  # Send data to the server

    client_socket.sendto(b'', server_address)  # Send an empty byte to signal the end of the file
    client_socket.close()

if __name__ == "__main__":
    file_to_send = 'file_to_send.txt'  # Replace with the path to the file you want to send
    udp_client(file_to_send)
