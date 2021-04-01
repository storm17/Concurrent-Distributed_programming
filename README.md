# Concurrent-Distributed_programming

Simulación del funcionamiento de un restaurante de comida rápida
Parte 1: Programación Concurrente

Se desea modelar el comportamiento de un grupo de 200 clientes (que serán modelados como hilos) que van dejando, cada uno de ellos, dos pedidos en un mostrador del restaurante. Cada cliente tardará entre 500 y 1000 ms entre dejar un pedido y otro. La capacidad máxima del “mostrador de pedidos” es de 10. Por otro lado, hay 2 empleados (que también deberán ser modelados como hilos) del restaurante que se encargan de coger los pedidos del mostrador de pedidos (de uno en uno), llevarlos a la cocina y dejarlos en la “mesa de platos” (que tiene una capacidad máxima de 20 pedidos) para que los cocineros preparen los distintos pedidos (los empleados tardan en hacer esta tarea entre 300 y 700 ms cada vez). En la cocina se encuentran 3 cocineros (que también deberán ser modelados como hilos) que se encargarán de coger los pedidos de la “mesa de platos” y cocinar cada plato. Los cocineros tardan en coger un pedido de la “mesa de platos” y prepararlo entre 1500 y 2000 ms.
Los clientes sólo podrán dejar pedidos en el “mostrador de pedidos” mientras haya hueco disponible, y si no deberán esperar a que lo haya. Por otro lado, los empleados deberán esperar a que haya pedidos disponibles en el “mostrador de pedidos” para poderlos llevar a la cocina. Si la “mesa de platos” está llena ya de pedidos, los empleados no podrán dejar nuevos pedidos hasta que en ésta haya hueco. Por último, si no hay pedidos listos para cocinar en la “mesa de platos”, los cocineros tendrán que esperar hasta que los haya.

Consideraciones a tener en cuenta:

 Cada cliente deberá tener un identificador único: “Cliente1”, “Cliente2”, etc.
 Los empleados tendrán los identificadores “Empleado1” y “Empleado2”.
 Los cocineros tendrán los identificadores “Cocinero1”, “Cocinero2” y “Cocinero3”.
 Una vez que un cliente ha depositado los dos pedidos en el “mostrador de pedidos”, este finaliza.
 Los empleados y cocineros no finalizarán nunca su ejecución.

Cada pedido estará identificado de la siguiente manera: “IDdeSuCliente+Px”. Por ejemplo, los
pedidos del “Cliente5” tendrán como identificador: “Cliente5-P1” y “Cliente5-P2”.
Los tiempos de los clientes y empleados se generarán aleatoriamente mediante las funciones random de
Java, y todo el comportamiento del sistema se guardará en un log (un fichero de texto llamado
“evolucionRestaurante.txt”), además de mostrarse gráficamente por pantalla, de forma que sea sencillo
analizar lo sucedido. El log guardará los eventos que van teniendo lugar, por ejemplo: “Cliente1 deja el
pedido Cliente1-P1 en el mostrador de pedidos”, “Empleado2 coge el pedido Cliente1-P1 y lo lleva a la mesa
de platos”, etc. En cada línea de dicho log deberá constar la marca de tiempo (incluyendo el segundo
determinado en el que tuvo lugar el evento) y el evento en sí.
Además, se deberá crear una interfaz gráfica donde se muestre la evolución del programa. 
Se deberá incluir un botón para pausar/reanudar toda la ejecución el programa, así como dos botones
independientes para pausar/reanudar a cada uno de los empleados.


Parte 2: Programación Distribuida

Basándose en la Parte 1 anterior, incluir un nuevo módulo capaz de visualizar de forma remota el
contenido del mostrador de pedidos y de la mesa de platos (utilizando programación concurrente distribuida).

Este nuevo módulo permitirá visualizar de forma remota el estado del mostrador de pedidos y de la mesa de
platos, y la información deberá ser actualizada continuamente de manera automática.

Se deben desarrollar, en total, dos programas:

 Un servidor, cuyo código base será el programa desarrollado en la Parte 1, ampliado con la
funcionalidad correspondiente para dar soporte al módulo de visualización de programación
distribuida.
 Un programa cliente (módulo de visualización) que permita comprobar el contenido del mostrador
de pedidos y de la mesa de platos.
Se podrán utilizar todos los mecanismos vistos en clase para resolver todos los problemas de
comunicación y sincronización que se plantean en este enunciado. No obstante, se deben utilizar los
mecanismos de sincronización y comunicación que resuelvan el problema de la forma más eficiente y
óptima posible.

