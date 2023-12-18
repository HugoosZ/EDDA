public class MinHeap {

    private int capacity;
    private int size ;
    private Video[] pq ;
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.pq = new Video[capacity];
        this.size = 0;
    }
    public int getSize() {return size;}

    public int getCapacity() {return capacity;}

    public Video getTop(){
        if(size == 0){
            throw new IllegalStateException("La lista está vacía");
        }
        else {
            return pq[0];
        }
    }



    private void swap(int v1, int v2){
        Video temp = pq[v1];
        pq[v1] = pq[v2];
        pq[v2] = temp;
    }

    private void swim(int k)  ////////////ACA TAMBIEN!! 1
    {
        int parent = (k-1)/2;
        if(k>0 && compare(pq[parent], pq[k] ) > 0 ){
            swap(k, parent); //(1) Se cambia el arreglo de posicion por solo la posicion
            swim(parent);
        }

    }

    public void insert( Video video) //ERRORRR ACA
    {
        if(size == capacity){
            throw new IllegalStateException("La lista está llena");
        }
        pq[size++] = video;

        swim(size - 1);

    }



    private int compare(Video v1, Video v2) //Posible falla!! (parent,k)
    {
        if(v1.getPopularity() == v2.getPopularity()){ //compara las weas por popularidad,, si tiene la misma popularidad compara por titulo
            return ( v1.getVideoTitle().compareTo(v2.getVideoTitle()));
        }
        else{
            return Float.valueOf(v1.getPopularity() - v2.getPopularity()).intValue(); //Posible error con float value of !!!!!! y el
        }
    }


    private void sink(int k) {
        int left = 2 * k+1;
        int right = 2 * k + 2;
        int smallest = k;

        if (left < size && compare(pq[left], pq[smallest]) < 0) { //se cambia la direccion del menor a mayor
            smallest = left;
        }

        if (right < size && compare(pq[right], pq[smallest]) < 0) { //se cambia la direccion del menor a mayor
            smallest = right;
        }

        if (smallest != k) {
            swap(k, smallest);
            sink(smallest);
        }
    }

    public Video Delete() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        Video video = pq[0];
        pq[0] = pq[size - 1];
        pq[size - 1] = null;
        size--;
        sink(0);
        return video;
    }

    public Video deleteMax(){
        if(size == 0){
            throw new IllegalStateException("La lista está vacía");
        }
        float Min = pq[0].getPopularity();
        int posicion = 0;
        for(int i = 0; i < size ; i++){
            if(pq[i].getPopularity() < Min){
                Min = pq[i].getPopularity();
                posicion = i;
            }
        }
        Video maxVideo = pq[posicion];
        pq[posicion] = pq[size - 1];
        size--;
        sink(posicion);
        pq[size] = null;
        return maxVideo;
    }

    public Video getVideo(int k)
    {
        if(k < size && k>= 0){
            return pq[k];
        }
        else{
            return null;
        }
    }

    public void printPriorityQueue()
    {
        for (int i= 0; i < size; i++ ){
            if(pq[i] != null)            {
                System.out.printf("%s ",getVideo(i).getVideoTitle());
            }
        }
        System.out.println();
    }



    public static void main(String[] args) {
        //Test cases
        MinHeap pq = new MinHeap(50);
        pq.insert( new Video("1","video 22f", "1","channel title", "12-12-1221",131, 323, 323,22f));
        pq.insert( new Video("2","video 12f", "1","channel title", "12-12-1221",131, 323, 323,12f));
        pq.insert( new Video("3","video 13f", "1","channel title", "12-12-1221",131, 323, 323,13f));
        pq.insert( new Video("4","video 14f", "1","channel title", "12-12-1221",131, 323, 323,14f));
        pq.insert( new Video("5","video 15f", "1","channel title", "12-12-1221",131, 323, 323,15f));
        pq.insert( new Video("6","video 16f", "1","channel title", "12-12-1221",131, 323, 323,16f));
        pq.insert( new Video("7","video 17f", "1","channel title", "12-12-1221",131, 323, 323,17f));
        pq.insert( new Video("8","video 18f", "1","channel title", "12-12-1221",131, 323, 323,18f));
        pq.insert( new Video("9","video 19f", "1","channel title", "12-12-1221",131, 323, 323,19f));
        pq.insert( new Video("10","video 20f", "1","channel title", "12-12-1221",131, 323, 323,20f));
        pq.insert( new Video("11","video 21f", "1","channel title", "12-12-1221",131, 323, 323,21f));
        pq.insert( new Video("12","video 11f", "1","channel title", "12-12-1221",131, 323, 323,11f));
        pq.printPriorityQueue();


        pq.printPriorityQueue();

        pq.deleteMax();
        pq.printPriorityQueue();
        pq.deleteMax();
        pq.printPriorityQueue();
        pq.deleteMax();
    }

}



