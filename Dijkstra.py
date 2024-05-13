import networkx as nx
import matplotlib.pyplot as plt

def dijkstra(graph, source):
    import heapq

    # Inicializa las distancias con infinito y cero para el origen
    distances = {node: float('infinity') for node in graph}
    distances[source] = 0
    priority_queue = [(0, source)]

    while priority_queue:
        current_distance, current_node = heapq.heappop(priority_queue)

        # Los nodos solo se procesan una vez, solo si la distancia es menor que la conocida
        if current_distance > distances[current_node]:
            continue

        for neighbor, weight in graph[current_node].items():
            distance = current_distance + weight

            # Solo considera este nuevo camino si es mejor
            if distance < distances[neighbor]:
                distances[neighbor] = distance
                heapq.heappush(priority_queue, (distance, neighbor))

    return distances


def cargar_grafo(archivo_rutas):
    G = nx.Graph()
    adj_list = {}
    with open(archivo_rutas, 'r') as file:
        for line in file:
            parts = line.strip().replace("\"", "").split(',')
            origen, destino, peso = parts[0].strip(), parts[1].strip(), float(parts[2].strip())
            G.add_edge(origen, destino, weight=peso)
            if origen not in adj_list:
                adj_list[origen] = {}
            if destino not in adj_list:
                adj_list[destino] = {}
            adj_list[origen][destino] = peso
            adj_list[destino][origen] = peso  # Asegura la simetría
    return G, adj_list

def mostrar_grafo(G):
    pos = nx.spring_layout(G)
    weights = nx.get_edge_attributes(G, 'weight')
    nx.draw(G, pos, with_labels=True, node_color='skyblue', node_size=500, edge_color='k', font_size=8)
    nx.draw_networkx_edge_labels(G, pos, edge_labels=weights)
    plt.show()

def calcular_mostrar_rutas(adj_list, origen):
    distancias = dijkstra(adj_list, origen)
    for destino, distancia in distancias.items():
        print(f"Costo a {destino}: {distancia}")

def main():
    archivo_rutas = './src/main/resources/rutas.txt'  # Cargar archivo de rutas
    G, adj_list = cargar_grafo(archivo_rutas)
    
    mostrar_grafo(G)
    
    origen = input("Ingrese la estación de salida: ")
    calcular_mostrar_rutas(adj_list, origen)

if __name__ == "__main__":
    main()