from sys import stdin

n,m = map(int,stdin.readline().split())

parties = []
answer = []
truth = set(map(int,stdin.readline().split()[1:]))

for _ in range(m):
    party = set(map(int,stdin.readline().split()[1:]))
    parties.append(party)
    answer.append(1)

for _ in range(m):
    for i,party in enumerate(parties):
        if truth & party:
            answer[i] = 0
            truth = truth|party

print(sum(answer))

