/*
-------------------------
NO MODIFICAR ESTE ARCHIVO
-------------------------
*/

public class MaxHeap {

    private int capacity;
    private Video[] pq;
    private int size;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.pq = new Video[capacity];
        this.size = 0;
    }


    public int getSize() {return size;}

    public int getCapacity() {return capacity;}

    public void clear(){
        this.pq = new Video[capacity];
        this.size = 0;
    }

    public Video getTop(){
        if(size == 0){
            throw new IllegalStateException("La lista está vacía");
        }
        else {
            return pq[0];
        }
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
        pq[size++] = video;
        swim(size-1);
    }

    private void sink(int k) {
        int left = 2*k+1;
        int right = 2*k+2;
        int greater = k;
        if (left < size && compare(pq[left], pq[greater]) > 0 ){
            greater = left;
        }
        if (right < size && compare(pq[right], pq[greater]) > 0){
            greater = right;
        }
        if (greater != k){
            swap(k, greater);
            sink(greater);
        }
    }

    public Video deleteMin(){
        if(size == 0){
            throw new IllegalStateException("La lista está vacía");
        }
        int lastLevel = size / 2;
        int min = size-1;
        Video minVideo;
        for (int i=lastLevel; i<size; i++){
            if(compare(pq[i], pq[min]) < 0){
                min = i;
            }
        }
        minVideo = pq[min];
        if(min != size-1){
            swap(size-1, min);
            swim(min);
        }
        size = size-1;


        return minVideo;
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
        for (int i=0; i < size; i++ ){
            if(pq[i] != null)            {
                System.out.printf("%s ",getVideo(i).getPopularity());
            }
        }
        System.out.println();
    }



    public static void main(String[] args) {
        MaxHeap pq = new MaxHeap(50);
        pq.insert( new Video("1","video 1", "1","channel title", "12-12-1221",131, 323, 323,22f));
        pq.insert( new Video("2","video 2", "1","channel title", "12-12-1221",131, 323, 323,12f));
        pq.insert( new Video("3","video 3", "1","channel title", "12-12-1221",131, 323, 323,13f));
        pq.insert( new Video("4","video 4", "1","channel title", "12-12-1221",131, 323, 323,14f));
        pq.insert( new Video("5","video 5", "1","channel title", "12-12-1221",131, 323, 323,15f));
        pq.insert( new Video("6","video 6", "1","channel title", "12-12-1221",131, 323, 323,16f));
        pq.insert( new Video("7","video 7", "1","channel title", "12-12-1221",131, 323, 323,17f));
        pq.insert( new Video("8","video 8", "1","channel title", "12-12-1221",131, 323, 323,18f));
        pq.insert( new Video("9","video 9", "1","channel title", "12-12-1221",131, 323, 323,19f));
        pq.insert( new Video("10","video 14", "1","channel title", "12-12-1221",131, 323, 323,20f));
        pq.insert( new Video("11","video 15", "1","channel title", "12-12-1221",131, 323, 323,21f));
        pq.insert( new Video("12","video 16", "1","channel title", "12-12-1221",131, 323, 323,11f));

        pq.printPriorityQueue();
        System.out.println(pq.getVideo(11).getVideoTitle());
        pq.deleteMin().reproduce();
        pq.printPriorityQueue();

    }

}



