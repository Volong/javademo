1. 初始化仓库

   `git init`

2. 将文件添加到暂存区

   `git add <file>`

3. 将文件提交到版本库

   `git commit -m <message>`

4. 重新提交

   `git commit --amend -m <message>`

   >   在发现提交信息写错了或者某些文件忘记添加的情况下使用

5. 查看仓库当前的状态

   `git status`

6. 查看文件的改动

   `git diff`

   >   比较的是工作区与暂存区的差异

   `git diff <commitid>`

   >   比较工作区与某次提交的差异

   `git diff [--staged | --cached]`

   >   比较暂存区与上一次提交的差异
   >
   >   加了 `<commitid>` 可以比较与某一个提交的差异 

7. 版本回退

   `HEAD` 表示当前版本

   `git reset --hard HEAD^` 退回到上次提交

   `git reset --hard HEAD^^` 退回到上上次提交。依此类推。

   `git reset --hard HEAD~n` 退回 `n` 次提交

   `git reset --hard commit-id`   退回到指定的 `commit-id` 处

   >   `reset --hard` 会重置工作区与暂存区中的内容。
   >
   >   `reset --soft` 会保留工作区与暂存区中的内容，并把文件之间的差异放进暂存区。
   >
   >   `reset ` 不加参数表示默认使用 `--mixed`，会保留工作区，但是会清空暂存区，并把文件之间的差异放进工作区。

1. 查看提交历史

   `git log`

   >   --pretty=online    提交信息只显示一行
   >
   >   --abbrev-commit 显示 commitid 的缩写
   >
   >   -p (--patch 的缩写)显示详细信息
   >
   >   --stat 显示统计信息

2. 查看操作历史

   `git reflog`

3. 查看当前提交的改动

    `git show`

4. 查看任意提交的改动

    `git show <commitid>`

5. 丢弃工作区的修改

    `git checkout -- <file>`

11. 丢弃暂存区的修改

    `git reset HEAD <file>`

12. 删除文件

    `rm <file> ` 或者在文件管理器中进行删除，则只是把文件从工作区删除了，但是在暂存区中还存在。

    使用 `git checkout -- <file>` 可以恢复删除的文件。

    `git rm <file>` 表示把文件从工作区与暂存区都删除了。

    使用 `git reset HEAD <file>` 将文件从暂存区恢复。

13. 先有本地仓库，后有远程仓库的情况下，将本地仓库与远程仓库关联起来

    `git remote add origin <giturl>` 

    `git push -u origin master` 

    或者

    `git push --set-upstream origin <branch>`

    >   第一次推送时的操作，后续的推送直接使用 `git push` 就可以了。

14. 从已存在的远程仓库进行克隆

    `git clone <giturl>`

    >   将生成跟远程仓库同名的文件夹

    `git clone <giturl> <dir>`

    >   将仓库放在指定的文件夹中

15. 新建分支

    `git branch <branch>`

16. 切换分支

    `git checkout <branch>`

17. 新建并切换分支

    `git checkout -b <branch>`

18. 将 `HEAD` 与当前分支进行分离

    `git checkout --detach`

19. 将指定分支合并到当前分支

    `git merge <branch>`

20. 取消合并

    `git merge --abort`

21. 变基

    `git rebase <commit>`

    >    将指定的 `commit` 之前的提交在当前分支上重新提交一遍。

22. 交互式变基

    `git rebase -i <commit>`

    >   可以用来修改或者删除指定的 `commit`

11. 查看分支本地分支

    `git branch`

    >   `-r` 表示查看远程分支
    >
    >   `-a` 表示查看本地与远程分支

12. 删除分支

    `git branch -d <branch>`

    >   在没有 `push` 的情况下 (做了 `add` 或者 `commit` 操作)，不能进行删除，但是可以强制删除。

13. 强行删除分支

    `git branch -D <branch>`

14. 隐藏文件

    `git stash`

    >   临时隐藏工作区文件的改动。默认情况下，没有被追踪的文件不会被隐藏。
    >
    >   `-u` 被追踪的文件也隐藏。

15. 隐藏文件的时候添加备注信息

    `git stash save -m <message>`

16. 隐藏文件的时候隐藏

17. 查看隐藏文件列表

    `git stash list`

18. 恢复隐藏文件

    `git stash apply <stash>`

    >   恢复之后需要进行手动删除

19. 在恢复的同时删除隐藏文件

    `git stash pop`

20. 删除某个隐藏文件

    `git stash drop <stash>`

21. 清空隐藏的文件

    `git stash clear`

22. 将本地分支与远程分支进行关联

    `git branch --set-upstream-to=origin/<branch> <branch>`

23. 打标签

    `git tag <name>`

24. 对某一次提交打标签

    `git tag <name> <commitid>`

25. 查看所有的标签

    `git tag`

26. 查看具体的标签

    `git show <tagname>`

27. 删除本地标签

    `git tag -d <tagname>`

28. 推送标签到远程

    `git push origin <tagname>`

29. 推送所有未推送的标签到远程

    `git push origin --tags`

30. 删除远程标签

    先删除本地标签，再执行 `git push origin :refs/tags/<tagname>`

31. 撤销提交

    `git revert <commitid>`


