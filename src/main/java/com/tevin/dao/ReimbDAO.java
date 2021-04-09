package com.tevin.dao;

import java.util.List;

import com.tevin.model.*;

public interface ReimbDAO {
	
	public Reimbursements getRequest(Integer id);
	public List<Reimbursements> allRequests();
	public List<Reimbursements> allPendingRequests();
	public List<Reimbursements> allResolvedRequests();
	public Boolean newRequest(Reimbursements request);
	public Boolean updateRequest(Reimbursements request);
	public Boolean removeRequest(Integer id);

}
