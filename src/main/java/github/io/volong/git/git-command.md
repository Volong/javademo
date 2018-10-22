1. 初始化仓库

   `git init`

2. 将文件添加到仓库

   `git add <file>`

3. 将文件提交到仓库

   `git commit -m <message>`

4. 查看仓库当前的状态

   `git status`

5. 查看文件的改动

   `git diff`

6. 版本回退

   `HEAD` 表示当前版本

   `git reset --hard HEAD^` 退回到上次提交

   `git reset --hard HEAD^^` 退回到上上次提交。依此类推。



   `git reset --hard HEAD~n` 退回 `n` 次提交

   `git reset --hard commit-id`   退回到指定的 `commit-id` 处

7. 查看提交历史

   `git log`

8. 查看操作历史

   `git reflog`

9. 丢弃工作区的修改

   `git checkout -- <file>`

10. 丢弃暂存区的修改

    `git reset HEAD <file>`

11. 删除文件

    `rm <file> ` 或者在文件管理器中进行删除，则只是把文件从工作区删除了，但是在暂存区中还存在。

    使用 `git checkout -- <file>` 可以恢复删除的文件。

    `git rm <file>` 表示把文件从工作区与暂存区都删除了。

    使用 `git reset HEAD <file>` 将文件从暂存区恢复。

12. 先有本地仓库，后有远程仓库的情况下，将本地仓库与远程仓库关联起来

    `git remote add origin <giturl>` 

    `git push -u origin master` 

    或者

    `git push --set-upstream origin <branch>`

    >   第一次推送时的操作，后续的推送直接使用 `git push` 就可以了。

13. 从已存在的远程仓库进行克隆

    `git clone <giturl>`

14. 新建分支

    `git branch <branch>`

15. 切换分支

    `git checkout <branch>`

16. 新建并切换分支

    `git checkout -b <branch>`

17. 将指定分支合并到当前分支

    `git merge <branch>`

18. 查看分支本地分支

    `git branch`

    >   `-r` 表示查看远程分支
    >
    >   `-a` 表示查看本地与远程分支

19. 删除分支

    `git branch -d <branch>`

20. 
