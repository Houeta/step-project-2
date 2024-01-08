Vagrant.configure("2") do |config|

  HOSTNAMES = ["jenkins-master", "jenkins-worker"]
  VAGRANT_VM_PROVIDER = "virtualbox"

  config.vm.box = "bento/ubuntu-22.04"

  config.vm.define HOSTNAMES[0] do |master|
      master.vm.hostname = HOSTNAMES[0]
      master.vm.network :private_network, ip: "192.168.56.10"
      master.vm.synced_folder "data/master", "/vagrant_data"
      master.vm.provision :docker do |docker|
          docker.build_image "/vagrant_data/docker", args: "-t custom-jenkins"
          docker.run HOSTNAMES[0],
            image: 'custom-jenkins',
            args: "-p 8080:8080 -p 50000:50000\
                  -u root\
                  -v /vagrant_data/jenkins_home:/var/jenkins_home\
                  -v /vagrant_data/jenkins_conf:/var/jenkins_conf\
                  --env-file /vagrant_data/jenkins_conf/.env.jenkins"
      end
      # master.vm.provision :shell, after: :each, inline: "bash /vagrant_data/init.sh"
  end

  config.vm.define HOSTNAMES[1] do |worker|
    worker.vm.hostname = HOSTNAMES[1]
    worker.vm.network :private_network, ip: "192.168.56.11"
    worker.vm.provision :ansible do |ansible|
      ansible.limit = "all"
      ansible.inventory_path = "./data/playbooks/hosts.ini"
      ansible.playbook = "./data/playbooks/playbook.yml"
    end
  end
end