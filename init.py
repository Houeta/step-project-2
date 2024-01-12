#!/usr/bin/python3
from getpass import getpass
from os import environ
from re import match


from dotenv import load_dotenv, set_key
from os.path import join, dirname
import validators

env_file = '.env.jenkins'
dotenv_path = join(dirname(__file__), 'data', 'master', 'jenkins_conf', env_file)
list_of_ENV = {
    'ADMIN_USER': ["Enter a username for Jenkins", "admin"],
    'ADMIN_PSW': ["Enter a password for Jenkins", "admin"],
    'ADMIN_EMAIL': ["Enter your email", "admin@example.com"],
    'GITHUB_URL': ["Enter an GitHub URL with your repo", "https://github.com/Houeta/step-project-2"],
    'DOCKER_USR': ["Enter your docker username"],
    'DOCKER_PSW': ["Enter your docker password"],
    }

def wrap_input(env, env_value):
    try:
        output_string = f"{env_value[0]} (by default \'{env_value[1]}\'):\n"
    except:
        output_string = f"{env_value[0]}:"

    if '_PSW' in env:
        user_value = getpass(prompt=output_string)
    elif '_EMAIL' in env:
        user_value = input(output_string)
        if user_value and not validate_email_syntax(user_value):
            wrap_input(env, env_value)
    elif '_URL' in env:
        user_value = input(output_string)
        if user_value and not validators.url(user_value):
            wrap_input(env, env_value)
    else:
        user_value = input(output_string)
    try:
        if not user_value:
            user_value = env_value[1]
    except Exception as e:
        user_value = ''
    return user_value

def set_default_value(env, env_value):
    try:
        default_value = env_value[1]
    except:
        default_value = ''
    set_variable_in_file(env, default_value)

def set_variable_in_file(key, value, env_file=dotenv_path):
    environ[key] = value
    set_key(env_file, key, environ[key], quote_mode="never")

def validate_email_syntax(email):
    if not email:
        return email
    pattern = r"^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9_.]+\.[a-zA-Z0-9_.]+$"
    return match(pattern, email)


if __name__ == '__main__':
    print('Hello. You must complete this part before Vagrantfile will work.')
    load_dotenv(dotenv_path)

    try:
        for env_key, env_value in list_of_ENV.items():
            set_variable_in_file(env_key, wrap_input(env_key, env_value))
    except Exception as e:
        print(e)
        for env_key, env_value in list_of_ENV.items():
            set_default_value(env_key, env_value)
            
