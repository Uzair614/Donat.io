
	        <div class="content">
	            <div class="container-fluid">
	                <div class="row">
	                    <div class="col-md-12">
	                        <div class="card">
	                            <div class="card-header" data-background-color="purple">
	                                <h4 class="title">Donation Requesters</h4>
	                                <p class="category">Approve or reject their requests</p>
	                            </div>
	                            <div class="card-content table-responsive">
	                                <table class="table">
	                                    <thead class="text-primary">
	                                    	<th>Requester Name</th>
	                                    	<th>Donation Type</th>
	                                    	<th>Phone</th>
	                                    	<th>CNIC</th>
	                                    	<th>Other Details</th>
																				<th>Options</th>
	                                    </thead>
	                                    <tbody>
																				<?php foreach($donationRequestList as $donationrequest): ?>
																					<tr>
																						<td><?= $donationrequest->getRequester()->getName()?></td>
																						<td><?= $donationrequest->getDType()?></td>
																						<td><?= $donationrequest->getRequester()->getPhone()?></td>
																						<td><?= $donationrequest->getRequester()->getCNIC()?></td>
																						<td><?= $donationrequest->getOther()?></td>
																						<?php if(!$donationrequest->getApprovedBy()): ?>
																							<td>
																								<button type="button" class="btn btn-primary" id="<?=$donationrequest->getdID().'-A'?>">Approve</button>
																							</td>
																							<td>
																								<button type="button" class="btn btn-danger" id="<?=$donationrequest->getdID().'-D'?>">Delete</button>
																							</td>
																						<?php else: ?>
																							<td>
																								<button type="button" class="btn btn-danger" id="<?=$donationrequest->getdID().'-R'?>">Reject</button>
																							</td>
																						<?php endif; ?>
																					</tr>
																				<?php endforeach; ?>
	                                    </tbody>
	                                </table>
	                            </div>
	                        </div>
	                    </div>
  								</div>
	            </div>
	        </div>
	    </div>
	</div>
