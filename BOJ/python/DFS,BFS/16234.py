from sys import stdin
from collections import deque
dx = [0,0,1,-1]
dy = [1,-1,0,0]

def BFS(i,j):
    q = deque()
    q.append([i,j])
    result = []
    result.append([i,j])
    while q:
        y,x = q.popleft()
        for k in range(4):
            new_x = x+dx[k]
            new_y = y+dy[k]
            if 0<=new_x<n and 0<=new_y<n and not visit[new_y][new_x]:
                if l<=abs(matrix[y][x]-matrix[new_y][new_x])<=r:
                    visit[new_y][new_x] = 1
                    q.append([new_y,new_x])
                    result.append([new_y,new_x])
    return result

n,l,r = map(int,stdin.readline().split())
matrix = []

for _ in range(n):
    matrix.append(list(map(int,stdin.readline().split())))

count = 0
while True:
    visit = [[0]*n for _ in range(n)]
    flag = 1
    for i in range(n):
        for j in range(n):
            if visit[i][j] == 0:
                visit[i][j] = 1
                result = BFS(i,j)
                if len(result) > 1:
                    flag = 0
                    union = sum([matrix[y][x] for y,x in result]) // len(result)
                    for y,x in result:
                        matrix[y][x] = union
    
    print(count,*matrix,sep='\n')
    

    if flag:
        break
    count += 1

print(count)