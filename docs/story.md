# 啰里啰唆

开源一款 spring + spring mvc + mybatis + rabbitmq 新手练手项目。

如果真的有帮助到大家，我会感到很高兴，因为自己在学习的过程中，  
也搜索过很多的资料，算是对技术人的一点回报吧。

如果大佬们觉得此代码质量很垃圾，也请默默翻过吧，  
因为，确实，本人也很菜，如果有误人子弟，还请包含。

# 项目起意

讲一下我学驾照的经历吧，  
先在网上查，我所在城市的哪家驾校口碑好，没查出个所以然，  
(所以这也算是一个需求吧，弄个社区，评比哪家驾校好)  

口碑没查到，查到地址，转线下实际探查一下吧，  
看环境，看人数，看车辆，看感觉，  
感觉成了，交钱，拿合同，备战科目一。

十天之后，刷完 2000 道题，考试，及格，分配教练，拉群，预约上车。

由于之前的四看，选了一家离大学城近的驾校，所以人很多，群里大概快200人，  
以为人很多，很热闹，但是一个群200人，每天预约人数 50 到 60 人，预约名额7人， 
这么多人对一个教练，很是心烦。

预约规则是在群里发送 练车时间，以及驾照类型(教练同时教C1, C2)，  
教练根据 聊天记录的发送时间（社交软件的系统时间）分配上车名额，在某时间节点，谁先发送谁就能预约成功。  
由于 iphone 手机有快捷指令，你懂得，像邮件一样的定时发送不就来了么，所以一旦到某一刻手机都 “炸了”。  
然后用记事本记录预约信息，复制粘贴发送到群里，反馈预约成功的最终结果。

有时也会因为可能网络问题，或其它问题，假设开始预约时间节点是6:00，明明在自己手机上是6:00，但是在对方手机上是5:59，那么预约信息将无效。

所以为了解决以上两个问题，

1. 翻聊天记录，统计预约信息，复制粘贴结果
2. 系统时间不统一

也就有了此项目。也因为想学习一下后端技术，因为我之前做的一直是前端岗位，没有完完整整的从前端到后端整个项目的编写，借此项目练练手。

# 关于我

找工作中，技术很菜，不善表达，如果有看的上我的 call me。924874135@163.com  
发此文，也是想推销一下自己。



