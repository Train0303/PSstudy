from sys import maxsize, stdin
import heapq as hq

def dijacstra(start):
    distances = {node:maxsize for node in range(1,n+1)}
    distances[start] = 0
    q = []
    hq.heapify(q)
    hq.heappush(q,(distances[start],start))
    while q:
        cur_dist, cur_node = hq.heappop(q)
        if cur_dist > distances[cur_node]:
            continue
        
        for adj_node,adj_dist in graph[cur_node].items():
            wei_dist = cur_dist + adj_dist
            if distances[adj_node] > wei_dist:
                distances[adj_node] = wei_dist
                hq.heappush(q,(wei_dist,adj_node))

    return distances

n,e = map(int,stdin.readline().split())
graph = {node:{} for node in range(1,n+1)}

for _ in range(e):
    a,b,c = map(int,stdin.readline().split())
    graph[a][b] = graph[b][a] = c

u,v = map(int,stdin.readline().split())
# print(graph)
result1_2 = dijacstra(1)[u]
result1_3 = dijacstra(1)[v]
result2 = dijacstra(u)[v]
result3_3 = dijacstra(u)[n]
result3_2 = dijacstra(v)[n]

result = min(result1_2+result2+result3_2 , result1_3+result2+result3_3)
print(-1 if result >= maxsize else result)