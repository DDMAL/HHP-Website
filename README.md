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
