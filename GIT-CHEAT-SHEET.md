# The git commands cheat sheet

For your support. More git documentation and tutorials can be found from [here](https://git-scm.com/doc) and if using Unix/Linux/macOS using the man command at terminal: `man git` or details about commit command: `man git-commit`. By the way, do you know who originally developed git?

| Command            | Example                                                     | Description                           |
|--------------------|-------------------------------------------------------------|---------------------------------------|
| git config *1)*    | git config --global user.name "John Doe"                    | Configures your name to git.          |
|                    | git config --global user.email johndoe@example.com          | Configures your email to git.         |
|            *2)*    | git config --global core.editor *some editor*               | Configures the editor used with git.  |
| git clone          | git clone git@gitlab.com:some-example-repository-here.git   | Copy remote repository to your PC.    |
| git status         | git status                                                  | See changed files in local repository.|
| git diff           | git diff                                                    | See all changes in local repository   |
| git log            | git log, git log SomeFile.java                              | See commits and commit messages.      |
| git add *3)*       | git add SomeFile.java                                       | Adds new/changed file to next commit. |
| git commit *4)*    | git commit -am"What was done here."                         | Commits changes to local repository.  |
| git push           | git push                                                    | Pushes *committed* changes to remote. |
| git pull *5)*      | git pull                                                    | Fetches changes from the remote.      |

Notes:

1. The name and email are used to indicate who made the commit.  If you want to use different email in this course project than what you use usually, give the `git config` command *without* the `--global` option in the *local repository directory*.
2. If you forget to use `-m` option with `git commit`, git uses the default text editor that it opens for you so that you can give the commit message. So set this to be something you would like to use. See instructions from [git-scm.com](https://git-scm.com/book/en/v2/Getting-Started-First-Time-Git-Setup) on how to do this. If you do not change the default editor, you can see something odd like vim launched you do not perhaps know how to use...
3. Note that when you create new files, they have to be added to local git repository using git add. `git commit -am` does *not* add new files not yet in git to the repository.
4. `git commit` *without* `-a` only commits files added to the commit using `git add <file>`. So the easiest way to add changed files *that are already in git repo* to commit is to use `git commit -am`. 
5. If you only edit the project in your own single PC and nowhere else, you should not need to use `git pull`. If you edit your project on several PCs or from the project web pages in GitLab, then you need to pull those changes to the local repository too. If you have changes in *both* local and remote repository, you may need to *merge* those changes. If the changes are in conflict, you need to *resolve* the conflicting changes. If you have no experience in using git, we recommend that you edit the project files *only on one PC* to avoid merge conflicts.
