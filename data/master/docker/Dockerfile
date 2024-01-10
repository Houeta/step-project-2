FROM jenkins/jenkins:lts-jdk17

ARG JAVA_OPTS
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt

RUN git config --global --add safe.directory "*" && mv .gitconfig /var/jenkins_home/