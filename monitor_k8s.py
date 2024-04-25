import subprocess
import time
import os

def get_kubectl_output():
        # Thực thi lệnh 'kubectl get all -o wide'
        process = subprocess.Popen(['kubectl', 'get', 'all', '-o', 'wide'])
        return process

while True:
    kubectl_output = get_kubectl_output()
    # os.system('clear')
    subprocess.Popen(['clear'])
    print(kubectl_output)
    time.sleep(2)  

