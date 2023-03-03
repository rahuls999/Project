# The csvserver assignment

Thanks for giving me opportunity to participate in this test. I have completed all the respective parts mentioned in this test. Kindly follow the below process for running csvserver on the machine.

## Steps

 - Clone this repository to your machine.
    - `git clone --single-branch --branch test-assignment https://github.com/rahuls999/TEST.git`

 - `cd` into the `solution` directory, and perform all the steps from that directory.

 - Running csvserver from docker command:
    - `docker run -itd -v /root/csvserver/solution/inputdata:/csvserver/inputdata -p 9393:9300 -e CSVSERVER_BORDER=Orange infracloudio/csvserver:lates`

 - Running csvserver and prometheus using docker-compose command:
   - `docker-compose up`

 - To check the respective containers running:
   - `docker ps`

 - Once you have run the apps containers, you can access the csvserver and prometheus using below link:
   - csvserver : http://localhost:9393
   - prometheus : http://localhost:9090


## GoodBye
~
