FROM jenkins/jenkins:lts-jdk17

ARG JAVA_OPTS
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install_plugins.sh < /usr/share/jenkins/ref/plugins.txt