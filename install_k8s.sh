#!/bin/bash

sudo timedatectl set-timezone Asia/Ho_Chi_Minh

sudo apt update -y
sudo apt upgrade -y

# Error dpkg => fix manual 

echo -e "link_file = '/etc/fstab'\ndef edit_file(link_file):\n    with open(link_file,'r') as file:\n        lines = file.readlines()\n\n    with open(link_file,'w') as file:\n        for line in lines:\n            if \"swap\" in line:\n                line = '#' + line\n            file.write(line)\n        print('Final')\n\nedit_file('/etc/fstab')" > edit_swap.py

sudo python3 edit_swap.py


sudo modprobe br_netfilter
sudo sysctl -w net.bridge.bridge-nf-call-ip6tables=1
sudo sysctl -w net.bridge.bridge-nf-call-iptables=1
sudo sysctl -w net.bridge.bridge-nf-call-arptables=1
sudo sysctl -p
echo 1 | sudo tee /proc/sys/net/ipv4/ip_forward
sudo sysctl --system
KUBERNETES_VERSION=1.29
sudo mkdir -p /etc/apt/keyrings
sudo curl -fsSL https://pkgs.k8s.io/core:/stable:/v$KUBERNETES_VERSION/deb/Release.key | sudo gpg --dearmor -o /etc/apt/keyrings/kubernetes-apt-keyring.gpg
echo "deb [signed-by=/etc/apt/keyrings/kubernetes-apt-keyring.gpg] https://pkgs.k8s.io/core:/stable:/v$KUBERNETES_VERSION/deb/ /" | sudo tee /etc/apt/sources.list.d/kubernetes.list

sudo apt update -y

sudo apt install -y kubelet=1.29.0-1.1 kubectl=1.29.0-1.1 kubeadm=1.29.0-1.1
sudo apt-mark hold kubelet kubeadm kubectl
sudo curl -fsSL https://pkgs.k8s.io/addons:/cri-o:/prerelease:/main/deb/Release.key | sudo gpg --dearmor -o /etc/apt/keyrings/cri-o-apt-keyring.gpg
echo "deb [signed-by=/etc/apt/keyrings/cri-o-apt-keyring.gpg] https://pkgs.k8s.io/addons:/cri-o:/prerelease:/main/deb/ /" | sudo tee /etc/apt/sources.list.d/cri-o.list
sudo apt update -y
sudo apt install -y cri-o
sudo systemctl daemon-reload
sudo systemctl enable crio --now
sudo systemctl start crio.service
VERSION="v1.28.0"
sudo wget https://github.com/kubernetes-sigs/cri-tools/releases/download/$VERSION/crictl-$VERSION-linux-amd64.tar.gz -O /tmp/crictl-$VERSION-linux-amd64.tar.gz
sudo tar zxvf /tmp/crictl-$VERSION-linux-amd64.tar.gz -C /usr/local/bin
sudo rm -f /tmp/crictl-$VERSION-linux-amd64.tar.gz
cat /proc/sys/net/ipv4/ip_forward
cat /proc/sys/net/bridge/bridge-nf-call-ip6tables
cat /proc/sys/net/bridge/bridge-nf-call-iptables
cat /proc/sys/net/bridge/bridge-nf-call-arptables


crictl --version

kubectl version
