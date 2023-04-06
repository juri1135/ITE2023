package Lab06;

public class Sort {

	public static void main(String[] args) {
		int[] arr1= {7,4,5,1,3};
		int[] arr2= {9,2,2,5,4,3,1,6,7,3,8,0,3};
		
		printArr(arr1); bubbleSort(arr1); printArr(arr1);
		printArr(arr2); bubbleSort(arr2); printArr(arr2);

	}
	public static void bubbleSort(int arr[]) {
		System.out.println("simple bubble sort");
		bubbleSort(arr,arr.length);
	}
	public static void bubbleSort(int arr[], int n) {
		for(int i=0; i<n; i++) {
			for(int k=i+1; k<n; k++) {
				int x;
				if(arr[i]>arr[k]) {
					x=arr[k];
					arr[k]=arr[i];
					arr[i]=x;
				}
			}
		}
	}
	public static void printArr(int arr[]) {
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
}
