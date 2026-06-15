import subprocess

target = input(
    "Target IP or Network: "
)

output_file = "scans/result.xml"

command = [
    "nmap",
    "-sV",
    "-O",
    "-oX",
    output_file,
    target
]

print("Running Nmap...")

subprocess.run(command)

print("Scan completed")
print(f"Results saved in {output_file}")