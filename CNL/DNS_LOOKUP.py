import socket

def dns_lookup(input):
    try:
        ip_address = socket.gethostbyname(input)
        host_name = socket.gethostbyaddr(input)
        print("Input:", input)
        if ip_address:
            print("IP Address:", ip_address)
        if host_name:
            print("Host Name:", host_name)
    except socket.gaierror:
        print("Could not resolve host:", input)

if __name__ == "__main__":
    user_input = input("Enter a URL or IP address: ")
    dns_lookup(user_input)
