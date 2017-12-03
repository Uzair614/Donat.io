
	        <div class="content">
	            <div class="container-fluid">
	                <div class="row">
	                    <div class="col-md-12">
	                        <div class="card">
	                            <div class="card-header" data-background-color="purple">
	                                <h4 class="title">Your Volunteers</h4>
	                                <p class="category">Approve or reject their requests</p>
	                            </div>
	                            <div class="card-content table-responsive">
	                                <table class="table">
	                                    <thead class="text-primary">
	                                    	<th>Name</th>
	                                    	<th>Phone</th>
	                                    	<th>CNIC</th>
																				<th>Options</th>
	                                    </thead>
	                                    <tbody>
																				<?php foreach($volunteerList as $volunteer): ?>
																					<tr>
																						<td><?= $volunteer->getName()?></td>
																						<td><?= $volunteer->getPhone()?></td>
																						<td><?= $volunteer->getCNIC()?></td>
																						<?php if(!$volunteer->getApproved()): ?>
																							<td>
																								<button type="button" class="btn btn-primary" id="<?=$volunteer->getvID().'-A'?>">Approve</button>
																							</td>
																							<td>
																								<button type="button" class="btn btn-danger" id="<?=$volunteer->getvID().'-D'?>">Delete</button>
																							</td>
																						<?php else: ?>
																							<td>
																								<button type="button" class="btn btn-danger" id="<?=$volunteer->getvID().'-R'?>">Reject</button>
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
