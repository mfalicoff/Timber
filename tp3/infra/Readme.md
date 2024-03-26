What's in the box: bento/ubuntu-20.04
Disk Space: 50GB primary disk
Network: Hooks up to the public network
Provisioning: Uses Ansible
    Cranks verbosity up a notch (-v)
    Needs sudo? Yes, and it'll ask for the password
    Compatibility mode: 2.0
VirtualBox Settings:
    No GUI, runs headless
    Allocated memory: 8GB

Make sure you've got VirtualBox, Vagrant, and Ansible installed

After installing, run vagrant up to start and provision the vm

Run vagrant up --provision to rerun the playbook

Run vagrant ssh to ssh into the vm

Run vagrant destroy to destroy the vm