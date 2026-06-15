import xml.etree.ElementTree as ET

tree = ET.parse(
    "scans/result.xml"
)

root = tree.getroot()

for host in root.findall("host"):

    address = host.find("address")

    if address is not None:

        print(
            f"\nHost: {address.attrib['addr']}"
        )

    ports = host.find("ports")

    if ports is None:
        continue

    for port in ports.findall("port"):

        service = port.find("service")

        if service is not None:

            print(
                f"Port {port.attrib['portid']} "
                f"- {service.attrib.get('name','Unknown')}"
            )