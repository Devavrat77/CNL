import ipaddress

def calculate_subnet(ip, subnet_bits):
    network = ipaddress.IPv4Network(ip, strict=False)
    subnet_mask = network.netmask.with_prefixlen + subnet_bits
    return network, subnet_mask

def main():
    ip_address = "192.168.1.0"  # Enter your IP address here
    num_subnets = 4
    num_hosts = 32

    subnet_bits = num_subnets.bit_length()
    host_bits = num_hosts.bit_length()

    if 2 ** (32 - (subnet_bits + host_bits)) < num_subnets:
        print("Error: Not enough bits for the given subnets and hosts.")
        return

    network, subnet_mask = calculate_subnet(ip_address, subnet_bits)
    print(f"Original Network: {network}")
    print(f"Subnet Mask: /{subnet_mask}")

if __name__ == "__main__":
    main()
