from sys import stdin
from collections import deque


def DFS(start):
    q = deque()
    visited = {node:0 for node in range(1,v+1)}
    q.append([start,0])
    visited[start] = 1
    max_dist = 0
    max_index = start
    dist = 0
    while q:
        node,dist = q.popleft()
        if dist > max_dist:
            max_dist = dist
            max_index = node
        
        for adj_node, adj_dist in tree[node].items():
            if visited[adj_node] == 0:
                visited[adj_node] = 1
                q.append([adj_node, dist+adj_dist])
    return max_dist,max_index

v = int(stdin.readline())
tree = {node:{} for node in range(1,v+1)}

for _ in range(v):
    data = list(map(int,stdin.readline().split()))
    vertex = data[0]
    for i in range((len(data)-1)//2):
        node = data[1+2*i]
        length = data[2+2*i]
        tree[vertex][node] = length

max_length, max_index = DFS(1)
max_length, max_index = DFS(max_index)

print(max_length)