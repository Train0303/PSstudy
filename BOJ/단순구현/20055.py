from sys import stdin
from collections import deque

n,k = map(int,stdin.readline().split())
belt = deque((map(int,stdin.readline().split())))
robot = deque([0 for _ in range(n)])
cnt = 0

while True:
    cnt += 1
    belt.appendleft(belt.pop())
    robot.appendleft(robot.pop())
    robot[n-1] = 0

    for i in range(n-1,0,-1):
        if robot[i-1] == 1 and belt[i] > 0 and robot[i] == 0:
            robot[i],robot[i-1] = 1,0
            belt[i] -= 1
    robot[n-1] = 0

    if belt[0] > 0 :
        robot[0] = 1
        belt[0] -= 1
    
    if belt.count(0) >= k:
        break

print(cnt)