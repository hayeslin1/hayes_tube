# 开源视频网站

## 相面： 
电脑版：
![电影](media/16099259155455/16100916023059.jpg)
![动漫](media/16099259155455/16100916397403.jpg)
![500](media/16099259155455/16100914793426.jpg)

手机版： 
![](media/16099259155455/16100916806489.jpg)

## 站点信息

网站包含类型：

> `电影`： `剧情片``动作片``喜剧片``恐怖片``战争片``爱情片``科幻片`

>  `电视剧`：`台湾剧``国产剧``日本剧``欧美剧``海外剧``韩国剧``香港剧`

>  `综艺`：`内地综艺``日韩综艺``欧美综艺``港台综艺`
 
>  `动漫`：`国产动漫``日韩动漫``欧美动漫``海外动漫``港台动漫`

>  `电视剧``综艺`以及`动漫`会随网站更新而持续更新

> 源码地址： https://github.com/hayeslin1/hayes_tube.git

> 网站首页地址： http://127.0.0.1:8080

## tips 

> 本站点最好配合[视频爬虫](https://github.com/hayeslin1/movies_python) , 

> 视频爬虫地址为`https://github.com/hayeslin1/movies_python` 

> ![](https://img.shields.io/badge/language-java-orange.svg)


## 提供联合脚本： 
```shell 

#!/bin/bash

yum install -y wget 
yum install -y git


mkdir -p /home/videos
cd /home/videos

git clone https://github.com/hayeslin1/hayes_tube.git 
git clone https://github.com/hayeslin1/movies_python.git 

wget http://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.6.2/binaries/apache-maven-3.6.2-bin.tar.gz

tar -zxvf apache-maven-3.6.2-bin.tar.gz

echo 'export MVN_HOME=/home/videos/apache-maven-3.6.2' >> /etc/profile
echo 'export PATH=$PATH:$MVN_HOME/bin' >> /etc/profile

source /etc/profile

cp /home/videos/hayes_tube/aliyun/settings.xml /home/videos/apache-maven-3.6.2/conf/settings.xml

cd /home/videos/hayes_tube/

source /etc/profile
mvn clean install 

nohup java -jar target/hayes_tube-0.0.1-SNAPSHOT.jar --server.port=8080  >> /home/videos/hayes_tube/catalina.out 2>&1 &


yum install -y epel-release
yum install -y python-pip

pip install --upgrade pip
pip install requests 
pip install bs4
pip install pymysql
pip install lxml
pip install logging 

cd /home/videos/movies_python

touch crew.sh
chmod +x crew.sh

echo '#!/bin/bash' >> crew.sh
echo 'source /etc/profile' >> crew.sh
echo '' >> crew.sh
echo 'python MainTask.py' >> crew.sh
echo 'python TV_Task.py' >> crew.sh
echo 'python Media_Task.py' >> crew.sh
echo 'python Anime_Task.py' >> crew.sh

bash crew.sh 

crontab -l > conf && echo "00 00 * * *  /bin/bash /home/videos/movies_python/crew.sh" >> conf 
crontab conf && rm -f conf

```


