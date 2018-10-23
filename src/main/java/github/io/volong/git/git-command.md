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

20. 强行删除分支

    `git branch -D <branch>`

21. 暂存文件

    `git stash`

22. 查看暂存文件列表

    `git stash list`

23. 恢复暂存文件

    `git stash apply <stash>`

    >   恢复之后需要进行手动删除

24. 在恢复的同时删除暂存文件

    `git stash pop`

25. 删除暂存文件

    `git stash drop <stash>`

26. 将本地分支与远程分支进行关联

    `git branch --set-upstream-to=origin/<branch> <branch>`

27. 打标签

    `git tag <name>`

28. 对某一次提交打标签

    `git tag <name> <commitid>`

29. 查看所有的标签

    `git tag`

30. 查看具体的标签

    `git show <tagname>`

31. 删除本地标签

    `git tag -d <tagname>`

32. 推送标签到远程

    `git push origin <tagname>`

33. 推送所有未推送的标签到远程

    `git push origin --tags`

34. 删除远程标签

    先删除本地标签，再执行 `git push origin :refs/tags/<tagname>`

