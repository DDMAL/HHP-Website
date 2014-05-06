DDMAL Middleman Readme
=======

Installation instructions for Middleman
-------
First, make sure you have rvm and ruby. Mac and Ubuntu should both have system Ruby, and to install RVM the following lines should work (check rvm.io to see if they've changed)...

	curl -sSL https://get.rvm.io | bash -s stable
	source ~/.rvm/scripts/rvm

The version of Middleman that's currently (5/2014) active works with Ruby 2.1. To make sure rvm has this, enter `rvm install 2.1`. You may get some errors while trying to do this (especially if it's the first thing you've done with Homebrew), but the Ruby logs and Homebrew debugger are generally verbose enough to figure out where the error is.

Navigate to the folder where you cloned this git repo to; there should be a "Gemfile" file in the same folder level.  
- Enter `rvm use 2.1` to let rvm know which version of Ruby you want to use.  
- Enter the `rvm gemset create _____` where `_____` is an identifier for the Ruby environment you'll be using.  
- Enter `rvm use 2.1@_____` to turn on the Ruby environment  
- Enter the command `bundle install` and it should immediately install every gem that you'll need for the project (from the Gemfile).  

From here, you should be good to go. Enter `middleman server` and command line should tell you what else to do.


Maintenance instructions for Middleman
-------
To install new gems, there are three quick steps:

- `gem install _____`
- Add ______ to your Gemfile with version information as necessary.
- `bundle install` to make it official.

Pushing code to the server
-------
This gets slightly more complicated. 

Before the first time:
- First, ask someone with root privileges to create a user account for you on the ddmal-webserver under the user group "wwweditors".
- Once that is set, on your machine, run `brew install ssh-copy-id`. 
- If you do not have an rsa keypair yet, run `ssh-keygen -t rsa` to get one and follow the instructions.
- Run `ssh-copy-id -i ~/.ssh/id_rsa.pub (username on the server)@(server's IP)`, removing the parentheses.
- On the server, run `eval $(ssh-agent)` then `ssh-add` and you should be set.

To actually upload the site:
- Open config.rb and make sure that under `activate :deploy` towards the bottom, `deploy.path` is `/srv/` followed by the correct site name. 
- From the main directory, run `middleman build` to refresh the built copy.
- From the same directory, run `USERNAME=(username on the server) middleman deploy`. (removing the parentheses again) This sets a temporary environmental variable that middleman will interpret as being your username.