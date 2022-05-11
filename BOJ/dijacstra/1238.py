from sys import maxsize, stdin
import heapq as hq


def dijacstra(node,city):
    distances = {node:maxsize for node in range(1,n+1)}
    distances[node] = 0
    queue = []
    hq.heappush(queue, (distances[node],node))
    while queue:
        current_distance, node = hq.heappop(queue)
        if distances[node] < current_distance:
            continue
            
        for adj_node, distance in city[node].items():
            wei_distance = current_distance + distance
            if wei_distance < distances[adj_node]:
                distances[adj_node] = wei_distance
                hq.heappush(queue,(distances[adj_node],adj_node))
    
    return distances

n,m,x = map(int,stdin.readline().split())
city = {node:{} for node in range(1,n+1)}
rev_city = {node:{} for node in range(1,n+1)}
for _ in range(m):
    s,e,t = map(int,stdin.readline().split())
    city[s][e] = t
    rev_city[e][s] = t

come = dijacstra(x,city)
go = dijacstra(x,rev_city)
print(max(map(lambda x: sum(x), zip(come.values(),go.values()))))