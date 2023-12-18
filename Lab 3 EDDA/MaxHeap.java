public class MaxHeap {

    private int capacity;
    private int size ;
    private Video[] pq ;
    public MaxHeap(int capacity) {
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

    private void swim(int k)
    {
        int parent = (k-1)/2;
        if(k>0 && compare(pq[parent], pq[k] ) < 0 ){
            swap(k, parent);
            swim(parent);
        }

    }

    public void insert( Video video)
    {
        if(size == capacity){
            throw new IllegalStateException("La lista está llena");
        }
        pq[size] = video;
        swim(size);
        size++;
    }

    public Video deleteMin(){
        if(size == 0){
            throw new IllegalStateException("La lista está vacía");
        }
        float Max = pq[0].getPopularity();
        int posicion = 0;
        for(int i = 0; i < size ; i++){
            if(pq[i].getPopularity() < Max){
                Max = pq[i].getPopularity();
                posicion = i;
            }
        }
        Video minVideo = pq[posicion];
        pq[posicion] = pq[--size];
        sink(posicion);
        pq[size] = null;
        return minVideo;
    }
    private int compare(Video v1, Video v2)
    {
        if(v1.getPopularity() == v2.getPopularity()){
            return ( v1.getVideoTitle().compareTo(v2.getVideoTitle()));
        }
        else{
            return Float.valueOf(v1.getPopularity() - v2.getPopularity()).intValue();
        }
    }
    public Video delete(){
        if(size == 0){
            throw new IllegalStateException("La lista está vacía");
        }
        Video video = pq[0];
        pq[0] = pq[--size];
        pq[size] = null;
        sink(0);
        return video;
    }

    private void sink(int k) {
        int left = 2 * k+1;
        int right = 2 * k + 2;
        int greater = k;

        if (left < size && compare(pq[left], pq[greater]) > 0) {
            greater = left;
        }

        if (right < size && compare(pq[right], pq[greater]) > 0) {
            greater = right;
        }

        if (greater != k) {
            swap(k, greater);
            sink(greater);
        }
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
        MaxHeap pq = new MaxHeap(50);
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

        pq.delete().reproduce();
        pq.printPriorityQueue();
        pq.delete().reproduce();
        pq.printPriorityQueue();
    }

}



