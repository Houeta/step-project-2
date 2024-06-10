# Jenkins CasC

## Overview

This repository contains a test application and infrastructure setup aimed at demonstrating DevOps practices. The project is divided into two main parts:
1. **Main Branch**: Contains the JavaScript test application.
2. **VMS Branch**: Contains the infrastructure setup scripts to install Jenkins, create users, and automate pipeline startup.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
  - [Main Application](#main-application)
  - [Infrastructure Setup](#infrastructure-setup)
- [Usage](#usage)
  - [Running the Application](#running-the-application)
  - [Using Jenkins Pipeline](#using-jenkins-pipeline)

## Prerequisites

- Python 3.x
- Docker
- Virtual Machine with Ubuntu (for Jenkins setup)
- Git

## Installation

### Main Application

1. Clone the repository:
    ```sh
    git clone https://github.com/Houeta/step-project-2.git
    cd step-project-2
    ```

2. Create a virtual environment and activate it:
    ```sh
    python3 -m venv venv
    source venv/bin/activate
    ```

3. Install the required dependencies:
    ```sh
    pip install -r requirements.txt
    ```

### Infrastructure Setup

1. Switch to the `vms` branch:
    ```sh
    git checkout vms
    ```

2. Run the infrastructure setup script:
    ```sh
    ./setup.sh
    ```

This script will install Jenkins, create a user, and start the pipeline automatically.

## Usage

### Running the Application

1. Ensure you are in the main branch:
    ```sh
    git checkout main
    ```

2. Run the application:
    ```sh
    python app.py
    ```

### Using Jenkins Pipeline

1. Access Jenkins on your browser at `http://<your-vm-ip>:8080`.
2. Log in with the credentials created during the setup.
3. Navigate to your pipeline job and click "Build Now".
