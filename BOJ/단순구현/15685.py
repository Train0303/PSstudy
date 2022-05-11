from sys import stdin

direction = [(0,1),(-1,0),(0,-1),(1,0)] #우 상 좌 하

matrix = [[0]*101 for _ in range(101)]
n = int(stdin.readline())
for _ in range(n):
    x,y,d,g = map(int,stdin.readline().split())
    dir_list = []
    dir_list.append(d)
    
    matrix[y][x] = 1
    y += direction[d][0]
    x += direction[d][1]
    matrix[y][x] = 1
    # 0세대
    
    for i in range(g):
        for i in range(len(dir_list)-1,-1,-1):
            next_dir = (dir_list[i]+1)%4
            y += direction[next_dir][0]
            x += direction[next_dir][1]
            matrix[y][x] = 1
            dir_list.append(next_dir)
    
count = 0
for i in range(100):
    for j in range(100):
        if matrix[i][j] == 1 and matrix[i+1][j] == 1 and matrix[i][j+1] == 1 and matrix[i+1][j+1] == 1:
            count += 1

print(count)