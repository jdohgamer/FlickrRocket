package com.bottlerocket.flickrrocket;

import java.util.ArrayList;

public class JsonResponse { 
	private photos photos;
	
	public JsonResponse.photos.Photo getPhotoAtIdx(int idx){
		return this.get_photos().getPhoto().get(idx);
	}
	public class photos{
		private int page;
		private int pages;
		private int perpage;
		private int total;
		private ArrayList<Photo> photo; 
		
		public photos(){
		}
		
		public class Photo{
			private String id;
			private String owner;
			private String secret;
			private String server;
			private String farm;
			private String title;
			private int ispublic;
			private int isfriend;
			private int isfamily;
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public String getOwner() {
				return owner;
			}
			public void setOwner(String owner) {
				this.owner = owner;
			}
			public String getSecret() {
				return secret;
			}
			public void setSecret(String secret) {
				this.secret = secret;
			}
			public String getServer() {
				return server;
			}
			public void setServer(String server) {
				this.server = server;
			}
			public String getFarm() {
				return farm;
			}
			public void setFarm(String farm) {
				this.farm = farm;
			}
			public String getTitle() {
				return title;
			}
			public void setTitle(String title) {
				this.title = title;
			}
			public int isIspublic() {
				return ispublic;
			}
			public void setIspublic(int ispublic) {
				this.ispublic = ispublic;
			}
			public int isIsfriend() {
				return isfriend;
			}
			public void setIsfriend(int isfriend) {
				this.isfriend = isfriend;
			}
			public int isIsfamily() {
				return isfamily;
			}
			public void setIsfamily(int isfamily) {
				this.isfamily = isfamily;
			}
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getPages() {
			return pages;
		}

		public void setPages(int pages) {
			this.pages = pages;
		}

		public int getPerpage() {
			return perpage;
		}

		public void setPerpage(int perpage) {
			this.perpage = perpage;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public ArrayList<Photo> getPhoto() {
			return photo;
		}

		public void setPhoto(ArrayList<Photo> photo) {
			this.photo = photo;
		}
	}
	public photos get_photos() {
		return photos;
	}
	public void set_photos(photos _photos) {
		this.photos = _photos;
	}
}
