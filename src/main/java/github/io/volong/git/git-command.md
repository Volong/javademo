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

9. 

