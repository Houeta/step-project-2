Vagrant.configure("2") do |config|

  HOSTNAMES = ["jenkins-worker", "jenkins-master"]
  VAGRANT_VM_PROVIDER = "virtualbox"

  config.vm.box = "bento/ubuntu-22.04"

  config.vm.define HOSTNAMES[0] do |worker|
    worker.vm.hostname = HOSTNAMES[0]
    worker.vm.network :private_network, ip: "192.168.56.11"
  end

  config.vm.define HOSTNAMES[1] do |master|
      master.vm.hostname = HOSTNAMES[1]
      master.vm.network :private_network, ip: "192.168.56.10"
      #master.vm.network :forwarded_port, guest: 8080, host: 8080
      master.vm.synced_folder "data/master", "/vagrant_data"

      master.vm.provision :ansible do |ansible|
        ansible.limit = "all"
        ansible.inventory_path = "./data/playbooks/hosts.ini"
        ansible.playbook = "./data/playbooks/playbook.yml"
      end

      master.vm.provision :docker do |docker|
          docker.build_image "/vagrant_data/docker", args: "-t custom-jenkins"
          docker.run HOSTNAMES[1],
            image: 'custom-jenkins',
            args: "-p 8080:8080 -p 50000:50000\
                  -u root\
                  -v /vagrant_data/jenkins_home:/var/jenkins_home\
                  -v /vagrant_data/jenkins_conf:/var/jenkins_conf\
                  -v /vagrant/.vagrant/machines/jenkins-worker/virtualbox/private_key:/var/jenkins_secrets/worker_private_key\
                  --env-file /vagrant_data/jenkins_conf/.env.jenkins"
      end
  end
end
