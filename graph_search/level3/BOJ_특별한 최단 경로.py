import sys
import heapq
input = sys.stdin.readline


def solv():
    N, E = map(int, input().split())
    graph = [[] for _ in range(N + 1)]
    for e in range(E):
        a, b, c = map(int, input().split())
        graph[a].append([c, b])
        graph[b].append([c, a])

    v1, v2 = map(int, input().split())

    v1Visited = [2000000000] * (N + 1)
    v1Heap = []
    v1Visited[v1] = 0
    heapq.heappush(v1Heap, [0, v1])
    while v1Heap:
        node = v1Heap.pop()
        if v1Visited[node[1]] != node[0]:
            continue
        for newNode in graph[node[1]]:
            if v1Visited[newNode[1]] > newNode[0] + v1Visited[node[1]]:
                heapq.heappush(v1Heap, [newNode[0] + v1Visited[node[1]], newNode[1]])
                v1Visited[newNode[1]] = newNode[0] + v1Visited[node[1]]

    v2Visited = [2000000000] * (N + 1)
    v2Heap = []
    v2Visited[v2] = 0
    heapq.heappush(v2Heap, [0, v2])
    while v2Heap:
        node = v2Heap.pop()
        if v2Visited[node[1]] != node[0]:
            continue
        for newNode in graph[node[1]]:
            if v2Visited[newNode[1]] > newNode[0] + v2Visited[node[1]]:
                heapq.heappush(v2Heap, [newNode[0] + v2Visited[node[1]], newNode[1]])
                v2Visited[newNode[1]] = newNode[0] + v2Visited[node[1]]

    a = v1Visited[1] + v1Visited[v2] + v2Visited[N]
    b = v2Visited[1] + v2Visited[v1] + v1Visited[N]
    c = v1Visited[1] + v2Visited[v1]*2 + v1Visited[N]
    d = v2Visited[1] + v1Visited[v2]*2 + v2Visited[N]
    if a > 2000000000 and b > 2000000000:
        print(-1)
    else:
        print(min([a,b,c,d]))
solv()
