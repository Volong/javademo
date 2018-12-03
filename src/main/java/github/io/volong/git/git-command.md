1. 初始化仓库

   `git init` 在当前文件夹中进行初始化

   `git init <dir>` 在指定文件夹中进行初始化

2. 将文件添加到暂存区

   `git add <file>..` 添加指定的文件，多个文件可以使用空格进行分隔

   `git add .` 添加所有的文件

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

8. 查看提交历史

   `git log`

   >   --pretty=online    提交信息只显示一行
   >
   >   --abbrev-commit 显示 commitid 的缩写
   >
   >   -p (--patch 的缩写)显示详细信息
   >
   >   --stat 显示统计信息

9. 查看操作历史

   `git reflog`

10. 查看当前提交的改动

    `git show`

11. 查看任意提交的改动

     `git show <commitid>`

12. 丢弃工作区的修改

     `git checkout -- <file>`

13. 丢弃暂存区的修改

     `git reset HEAD <file>`

14. 删除文件

     `rm <file> ` 或者在文件管理器中进行删除，则只是把文件从工作区删除了，但是在暂存区中还存在。使用 `git checkout -- <file>` 可以恢复删除的文件。

     `git rm <file>` 表示把文件从工作区与暂存区都删除了。

    >   该文件需要被追踪才可以被删除

    `git rm --cached <file>` 从暂存区删除文件。

     使用 `git reset HEAD <file>` 将文件从暂存区恢复。

15. 先有本地仓库，后有远程仓库的情况下

     先将本地仓库与远程仓库关联起来：
     `git remote add origin <giturl>` 

     再进行推送：
     `git push -u origin master`  或者 `git push --set-upstream origin <branch>`

    >   第一次推送时的操作，后续的推送直接使用 `git push` 就可以了
    >
    >   origin 是对远程仓库的命名。一般为这个，也可以改成其他的名字。

16. 从已存在的远程仓库进行克隆

     `git clone <giturl>`

     >   将生成跟远程仓库同名的文件夹

     `git clone <giturl> <dir>`

     >   将仓库放在指定的文件夹中

17. 新建分支

     `git branch <branch>` 或 `git fetch origin :<branch-name>`

    在当前提交上创建分支

     `git branch <branch> <commitid>`

    在指定提交上创建分支

18. 切换分支

     `git checkout <branch>`

19. 新建并切换分支

     `git checkout -b <branch>`

20. 将 `HEAD` 与当前分支进行分离

     `git checkout --detach`

21. 将指定分支合并到当前分支

     `git merge <branch>`

22. 取消合并

     `git merge --abort`

23. 变基

     `git rebase <commit>`

     >    将指定的 `commit` 之前的提交在当前分支上重新提交一遍。

24. 交互式变基

     `git rebase -i <commit>`

     >   可以用来修改或者删除指定的 `commit`

25. 查看分支本地分支

     `git branch`

    >   `-r` 表示查看远程分支
    >
    >   `-a` 表示查看本地与远程分支

26. 删除本地分支

     `git branch -d <branch>`

     >   在没有 `push` 的情况下 (做了 `add` 或者 `commit` 操作)，不能进行删除，但是可以强制删除。

27. 强行删除本地分支

     `git branch -D <branch>`

28. 删除远程分支

    `git push origin :<branch-name>`

29. 隐藏文件

     `git stash`

    >   临时隐藏工作区文件的改动。默认情况下，没有被追踪的文件不会被隐藏。
    >
    >   `-u` 被追踪的文件也隐藏。

30. 隐藏文件的时候添加备注信息

     `git stash save -m <message>`

31. 隐藏文件的时候隐藏

32. 查看隐藏文件列表

     `git stash list`

33. 恢复隐藏文件

     `git stash apply <stash>`

     >   恢复之后需要进行手动删除

34. 在恢复的同时删除隐藏文件

     `git stash pop`

35. 删除某个隐藏文件

     `git stash drop <stash>`

36. 清空隐藏的文件

     `git stash clear`

37. 将本地分支与远程分支进行关联

     `git branch --set-upstream-to=origin/<branch> <branch>`

38. 打标签

     `git tag <name>`

    >   默认对当前的提交打标签
    >
    >   `-m` 可以对本次标签添加备注信息

39. 对某一次提交打标签

     `git tag <name> <commitid>`

40. 查看所有的标签

     `git tag`

41. 查看具体的标签

     `git show <tagname>`

42. 删除本地标签

     `git tag -d <tagname>`

43. 推送标签到远程

     `git push origin <tagname>`

44. 推送所有未推送的标签到远程

     `git push origin --tags`

45. 删除远程标签

     先删除本地标签，再执行 `git push origin :refs/tags/<tagname>`

46. 撤销本次的更改

     `git revert <commitid>`

    > 会产生一条提交记录

47. 更新 Git bash

    `git update-git-for-windows`

48. 将某些提交合并到当前分支

    `git cherry-pick <commitid>...`

49. 将指定分支切换到某次提交上

    `git branch -f <branch> <commitid>`

50. 清理尚未被追踪的文件

    `git clean -df`

    >   `-d` 表示移除文件夹
    >
    >   `-f` 表示强制
